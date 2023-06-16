package data_classes

data class ToDoItem(
    val id: String,
    val isCompleted: Boolean,
    val text: String?,
    val deadlineFlag: Boolean?,
    val dateCreated: String?,
    val deadline: String?
)