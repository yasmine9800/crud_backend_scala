package org.openapitools.server.model

import java.time.LocalDate

/**
 * @param id  for example: ''null''
 * @param lastname  for example: ''null''
 * @param firstname  for example: ''null''
 * @param address  for example: ''null''
 * @param birthdate  for example: ''null''
*/
final case class Student (
  id: Long,
  lastname: String,
  firstname: String,
  address: String,
  birthdate: String
)

