package br.com.guilhermegarciadev.restaurant.domain.valueobject

enum class Cuisine(
    val id: Int,
    val cuisineName: String
) {
    AMERICAN(1,"American"),
    CHINESE(2,"Chinese"),
    THAI(3,"Thai"),
    ITALIAN(4,"Italian"),
    FRENCH(5,"French"),
    JAPANESE(6,"Japanese"),
    TURKISH(7,"Turkish"),
    KOREAN(8,"Korean"),
    VIETNAMESE(9,"Vietnamese"),
    INDIAN(10,"Indian"),
    SPANISH(11,"Spanish"),
    GREEK(12,"Greek"),
    MEXICAN(13,"Mexican"),
    MALAYSIAN(14,"Malaysian"),
    AFRICAN(15,"African"),
    GERMAN(16,"German"),
    INDONESIAN(17,"Indonesian"),
    RUSSIAN(18,"Russian"),
    OTHER(19,"Other");

    companion object {
        fun byId(id: Int) : Cuisine? {
            return Cuisine.values().find { it.id == id }
        }
    }

    fun matches(criteria: String?): Boolean {
        return criteria == null || this.cuisineName.equals(criteria, true)
    }
}
