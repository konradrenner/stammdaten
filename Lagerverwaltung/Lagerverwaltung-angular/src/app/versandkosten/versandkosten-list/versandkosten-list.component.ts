import {Component, OnInit} from '@angular/core';
import { VersandkostenHttpService } from '../shared/versandkosten-http.service';
import {Observable} from 'rxjs/Observable';
import { ActivatedRoute } from '@angular/router';
import { Versandkosten } from './../shared/versandkosten'

@Component({
  selector: 'versandkosten-list',
  styleUrls: ['./versandkosten-list.component.css'],
  templateUrl: './versandkosten-list.component.html',
})
export class VersandkostenListComponent implements OnInit {
    land: string;
    laender: Observable<Array<Versandkosten>>;

    constructor(public httpService: VersandkostenHttpService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.land = params['land'];
        if (this.land) {
            this.laender = this.httpService.getAlleLaender();
      }
    });
  }
}
