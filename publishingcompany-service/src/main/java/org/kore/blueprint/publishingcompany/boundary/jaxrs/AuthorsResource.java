package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import org.kore.blueprint.publishingcompany.control.AuthorRepository;
import org.kore.blueprint.publishingcompany.control.BookService;
import org.kore.blueprint.publishingcompany.control.ChangeBookPriceCommand;
import org.kore.blueprint.publishingcompany.entity.author.Author;
import org.kore.blueprint.publishingcompany.entity.author.AuthorFactory;
import org.kore.blueprint.publishingcompany.entity.author.BlogPost;
import org.kore.blueprint.publishingcompany.entity.author.Book;
import org.kore.blueprint.publishingcompany.entity.author.Currency;
import org.kore.blueprint.publishingcompany.entity.author.ISBN;
import org.kore.blueprint.publishingcompany.entity.author.Price;
import org.kore.blueprint.publishingcompany.entity.author.PublicationID;
import org.kore.blueprint.publishingcompany.entity.author.events.AuthorAlreadyCreated;
import org.kore.blueprint.publishingcompany.entity.author.events.AuthorNotFound;
import org.kore.blueprint.publishingcompany.entity.author.events.BookNotFound;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/authors")
public class AuthorsResource {

    private static final Logger LOG = Logger.getLogger(AuthorsResource.class.getName());

    @Inject
    AuthorRepository repo;

    @Inject
    BookService bookService;

    @Inject
    AuthorFactory authorFactory;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<@Valid AuthorModel> getAllAuthors() {

        LOG.info("loading all authors");

        Collection<AuthorModel> models = new ArrayList<>();
        repo.findAll().stream().map(AuthorModel::new).forEach(models::add);

        LOG.log(Level.INFO, "found authors:{0}", models);

        return models;
    }

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthor(@PathParam("uuid") @NotNull @Pattern(regexp = AuthorModel.UUID_REGEX) String uuid) {

        LOG.log(Level.INFO, "searching for author with uuid:{0}", uuid);

        Optional<Author> author = repo.find(UUID.fromString(uuid));

        LOG.log(Level.INFO, "found author:{0}", author.orElse(null));


        if (author.isPresent()) {
            return Response.ok(new AuthorModel(author.get())).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAuthor(@NotNull @Valid AuthorModel model) {

        LOG.log(Level.INFO, "creating author:{0}", model);

        AuthorFactory.WithPublicationBuilder builder = authorFactory.createAuthor(UUID.randomUUID()).firstname(model.firstname).lastname(model.lastname);

        model.blogPosts.stream().map(this::mapBlogPost).forEach(builder::addBlogPost);
        model.books.stream().map(this::mapBook).forEach(builder::addBook);

        try {
            Author createdAuthor = repo.insert(builder.build());

            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(createdAuthor.getId().toString());

            LOG.log(Level.INFO, "author created with uuid:{0}", createdAuthor.getId());

            return Response.created(uriBuilder.build()).build();
        } catch (AuthorAlreadyCreated alreadyThere) {
            LOG.log(Level.WARNING, "author already exists");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{uuid}/books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateBook(@PathParam("uuid") String authorId, @PathParam("isbn") String isbn, @Valid @NotNull BookModel book) {

        LOG.log(Level.INFO, "updating book => authorId: {0}; isbn: {1}; new book-data: {2}", new String[]{authorId, isbn, book.toString()});

        UpdateBookRequest request = new UpdateBookRequest(authorId, isbn, book.price, book.currency);

        try {
            bookService.changeBookPrice(request);

            LOG.log(Level.INFO, "update done");

            return Response.ok().build();
        } catch (AuthorNotFound | BookNotFound nf) {
            LOG.log(Level.WARNING, "book or author not found:{0}", nf);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{uuid}/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(
            @PathParam("uuid") @NotNull @Pattern(regexp = AuthorModel.UUID_REGEX) String authorId,
            @PathParam("isbn") @NotNull @NotBlank @Size(min = 13, max = 13) String isbn) {

        LOG.log(Level.INFO, "searching book => authorId: {0}; isbn: {1}", new String[]{authorId, isbn});

        Optional<Book> book = repo.find(UUID.fromString(authorId)).flatMap(author -> author.getBook(new ISBN(isbn)));

        LOG.log(Level.INFO, "book:{0}", book.orElse(null));

        if (book.isPresent()) {
            return Response.ok(new BookModel(book.get())).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    class UpdateBookRequest implements ChangeBookPriceCommand {

        private final UUID authorId;
        private final ISBN isbn;
        private final Price newPrice;

        public UpdateBookRequest(String authorId, String isbn, BigDecimal newPrice, String currency) {
            this.authorId = UUID.fromString(authorId);
            this.isbn = new ISBN(isbn);
            this.newPrice = new Price(newPrice, Currency.valueOf(currency));
        }

        @Override
        public UUID getAuthorId() {
            return authorId;
        }

        @Override
        public ISBN getISBN() {
            return isbn;
        }

        @Override
        public Price getNewPrice() {
            return newPrice;
        }

        @Override
        public String toString() {
            return "UpdateBookRequest{" + "authorId=" + authorId + ", isbn=" + isbn + ", newPrice=" + newPrice + '}';
        }
    }

    Book mapBook(BookModel book) {
        return new Book(new ISBN(book.isbn), book.title, book.description, book.publishingDate, new Price(book.price, Currency.valueOf(book.currency)), book.pages);
    }

    BlogPost mapBlogPost(BlogPostModel post) {
        return new BlogPost(new PublicationID(post.blogId), post.title, post.description, post.publishingDate, post.location);
    }
}
