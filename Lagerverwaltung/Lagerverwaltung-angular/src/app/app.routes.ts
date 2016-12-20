import { Routes } from '@angular/router';

import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { VersandkostenListComponent } from './versandkosten/versandkosten-list/versandkosten-list.component';
import { VersandkostenDetailComponent } from './versandkosten/versandkosten-detail/versandkosten-detail.component';

export const rootRouterConfig: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  {
      path: 'versandkosten', component: VersandkostenListComponent,
      children: [
          { path: '', component: VersandkostenDetailComponent },
          { path: ':detail', component: VersandkostenDetailComponent }
        ]
  }
];

