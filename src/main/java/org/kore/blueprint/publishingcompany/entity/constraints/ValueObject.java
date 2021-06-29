/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 *
 * @author Konrad Renner
 */
@Inherited
@Target(ElementType.TYPE)
public @interface ValueObject {
// Value Object Marker, just a showcase which demonstrate how DDD architectural constraints can be controlled via ArchUnit
}
