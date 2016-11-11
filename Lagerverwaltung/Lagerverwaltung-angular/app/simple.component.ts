/* 
 * Copyright (C) 2016 Konrad Renner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import { Component } from "angular2/core"

@Component({
    selector: "simple-component",
    template: `
        <h1>Hallo Angular 2!</h1>
        <div>
            <label [hidden]="!isCalling">Ich bin {{name}}.</label>
            <div>
                <input [(ngModel)]="name" />
                <button (click)="toggleCalling()">toggle Calling</button>
            <div>
        </div>
        `
})
export class SimpleComponent {
    private name = "da";
    private isCalling = true;

    private toggleCalling() {
        this.isCalling = !this.isCalling;
    }
}
