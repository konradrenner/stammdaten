import { Injectable } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { Versandkosten } from './../shared/versandkosten';

@Injectable()
export class VersandkostenHttpService {
  constructor(private http: Http) {}

  getLand(land: string): Observable<Versandkosten> {
      let params = new URLSearchParams();

      let url = `http://stammdaten-kuk.rhcloud.com/Lagerverwaltung-rest/webresources/${land}`;
      return this.http.get(url, { search: params })
          .map((res) => res.json());
  }

  getAlleLaender(): Observable<Array<Versandkosten>> {
      let params = new URLSearchParams();

      let url = `http://stammdaten-kuk.rhcloud.com/Lagerverwaltung-rest/webresources`;
      return this.http.get(url, { search: params })
          .map((res) => res.json());
  }
}
