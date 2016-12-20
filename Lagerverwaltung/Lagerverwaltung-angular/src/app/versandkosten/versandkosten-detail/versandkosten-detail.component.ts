import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { VersandkostenHttpService } from '../shared/versandkosten-http.service';

@Component({
    selector: 'versandkosten-detail',
  styleUrls: ['./versandkosten-detail.component.css'],
  templateUrl: './versandkosten-detail.component.html'
})
export class VersandkostenDetailComponent implements OnInit {
  private org:string;
  private repo:string;
  public repoDetails:any = {};

  constructor(public github: VersandkostenHttpService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.org = this.route.snapshot.parent.params['org'];
      this.repo = params['repo'] || '';

      if (this.repo) {
        this.github.getRepoForOrg(this.org, this.repo)
          .subscribe(repoDetails => {
            this.repoDetails = repoDetails;
          });
      }
    });
  }
}
