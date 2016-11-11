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
System.register(["angular2/core"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1;
    var SimpleComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            SimpleComponent = (function () {
                function SimpleComponent() {
                    this.name = "da";
                    this.isCalling = true;
                }
                SimpleComponent.prototype.toggleCalling = function () {
                    this.isCalling = !this.isCalling;
                };
                SimpleComponent = __decorate([
                    core_1.Component({
                        selector: "simple-component",
                        template: "\n        <h1>Hallo Angular 2!</h1>\n        <div>\n            <label [hidden]=\"!isCalling\">Ich bin {{name}}.</label>\n            <div>\n                <input [(ngModel)]=\"name\" />\n                <button (click)=\"toggleCalling()\">toggle Calling</button>\n            <div>\n        </div>\n        "
                    }), 
                    __metadata('design:paramtypes', [])
                ], SimpleComponent);
                return SimpleComponent;
            }());
            exports_1("SimpleComponent", SimpleComponent);
        }
    }
});
//# sourceMappingURL=simple.component.js.map