package id.bachtiar.harits.moviecatalogue.data

data class DataResult<T>(val status: ViewState, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): DataResult<T> = DataResult(ViewState.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): DataResult<T> = DataResult(ViewState.ERROR, data, msg)

        fun <T> loading(data: T?): DataResult<T> = DataResult(ViewState.LOADING, data, null)
    }
}