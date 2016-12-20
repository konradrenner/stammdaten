import { NgModule } from '@angular/core'
import { RouterModule } from '@angular/router';
import { rootRouterConfig } from './app.routes';
import { AppComponent } from './app.component';
import { VersandkostenHttpService } from './versandkosten/shared/versandkosten-http.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';

import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { VersandkostenListComponent } from './versandkosten/versandkosten-list/versandkosten-list.component';
import { VersandkostenDetailComponent } from './versandkosten/versandkosten-detail/versandkosten-detail.component';

@NgModule({
  declarations: [
      AppComponent,
      AboutComponent,
      VersandkostenListComponent,
      VersandkostenDetailComponent,
      HomeComponent
    ],
  imports: [
      BrowserModule,
      FormsModule,
      ReactiveFormsModule,
      HttpModule,
      RouterModule.forRoot(rootRouterConfig, { useHash: true })
  ],
  providers: [
      VersandkostenHttpService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {

}
