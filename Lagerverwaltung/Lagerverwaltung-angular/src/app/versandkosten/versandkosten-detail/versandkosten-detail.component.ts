import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { VersandkostenHttpService } from '../shared/versandkosten-http.service';
import { Versandkosten } from './../shared/versandkosten'

@Component({
    selector: 'versandkosten-detail',
  styleUrls: ['./versandkosten-detail.component.css'],
  templateUrl: './versandkosten-detail.component.html'
})
export class VersandkostenDetailComponent implements OnInit {
    private land: string;
    public versandkostenDetails: Versandkosten;

    constructor(public httpService: VersandkostenHttpService, private route: ActivatedRoute) {
  }

  ngOnInit() {
      this.route.params.subscribe(params => {
          this.land = this.route.snapshot.parent.params['land'];
          if (this.land) {
              this.httpService.getLand(this.land)
                  .subscribe(versandkostenDetails => {
                      this.versandkostenDetails = versandkostenDetails;
                  });
          }
      });
  }
}
