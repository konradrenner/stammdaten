"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var app_routes_1 = require('./app.routes');
var app_component_1 = require('./app.component');
var github_service_1 = require('./github/shared/github.service');
var forms_1 = require('@angular/forms');
var platform_browser_1 = require('@angular/platform-browser');
var http_1 = require('@angular/http');
var about_component_1 = require('./about/about.component');
var home_component_1 = require('./home/home.component');
var repo_browser_component_1 = require('./github/repo-browser/repo-browser.component');
var repo_list_component_1 = require('./github/repo-list/repo-list.component');
var repo_detail_component_1 = require('./github/repo-detail/repo-detail.component');
var contact_component_1 = require('./contact/contact.component');
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
                about_component_1.AboutComponent,
                repo_browser_component_1.RepoBrowserComponent,
                repo_list_component_1.RepoListComponent,
                repo_detail_component_1.RepoDetailComponent,
                home_component_1.HomeComponent,
                contact_component_1.ContactComponent
            ],
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                forms_1.ReactiveFormsModule,
                http_1.HttpModule,
                router_1.RouterModule.forRoot(app_routes_1.rootRouterConfig, { useHash: true })
            ],
            providers: [
                github_service_1.GithubService
            ],
            bootstrap: [app_component_1.AppComponent]
        })
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
