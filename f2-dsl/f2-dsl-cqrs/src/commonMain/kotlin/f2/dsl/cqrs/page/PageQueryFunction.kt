package f2.dsl.cqrs.page

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

@JsExport
@JsName("PageQueryDTO")
interface PageQueryDTO : Query {
	val pagination: OffsetPaginationDTO?
}

@JsExport
@JsName("PageQueryResultDTO")
interface PageQueryResultDTO<out OBJECT> : Event, PageDTO<OBJECT> {
	override val total: Int
	override val items: List<OBJECT>
	val pagination: OffsetPaginationDTO?
}

@Serializable
@JsExport
@JsName("PageQuery")
class PageQuery(
	override val pagination: OffsetPaginationDTO?,
) : PageQueryDTO

@Serializable
@JsExport
@JsName("PageQueryResult")
class PageQueryResult<out OBJECT>(
	override val pagination: OffsetPagination?,
	override val total: Int,
	override val items: List<OBJECT>,
) : PageQueryResultDTO<OBJECT>
