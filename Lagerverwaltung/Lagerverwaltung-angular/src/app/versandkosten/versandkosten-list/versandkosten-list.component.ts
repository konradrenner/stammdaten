import {Component, OnInit} from '@angular/core';
import { VersandkostenHttpService } from '../shared/versandkosten-http.service';
import {Observable} from 'rxjs/Observable';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'versandkosten-list',
  styleUrls: ['./versandkosten-list.component.css'],
  templateUrl: './versandkosten-list.component.html',
})
export class VersandkostenListComponent implements OnInit {
  org: string;
  repos: Observable<any>;

  constructor(public github: VersandkostenHttpService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.org = params['org'];
      if (this.org) {
        this.repos = this.github.getReposForOrg(this.org);
      }
    });
  }
}
