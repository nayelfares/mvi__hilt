package com.izadinia.mviwithhilt.util

/**
 * it provides a way to map domain models onto entities and vice versa
 */
interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
}