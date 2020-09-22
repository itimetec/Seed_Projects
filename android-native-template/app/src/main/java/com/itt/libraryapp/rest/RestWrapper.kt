package com.itt.libraryapp.rest

import com.itt.libraryapp.utils.Const
import okhttp3.*

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */
class RestWrapper(private val client: OkHttpClient) {

    fun getUserByEmail(email: String): Call {
        val urlBuilder = HttpUrl.parse(Const.BASE_URL+"users")?.newBuilder()
                ?.addQueryParameter("email", email)

        return Request.Builder()
                .url(urlBuilder?.build())
                .get()
                .build()
                .let {
                    client.newCall(it)
                }
    }

    fun createUser(email: String,password: String): Call {
        val urlBuilder = HttpUrl.parse(Const.BASE_URL+"users")?.newBuilder()

        return Request.Builder()
                .url(urlBuilder?.build())
                .addHeader(Const.CONTENT_TYPE,Const.APPLICATION_JSON)
                .post(FormBody.Builder()
                        .add("email",email)
                        .add("password",password)
                        .build())
                .build()
                .let {
                    client.newCall(it)
                }
    }

    fun authenticateUser(email: String,password: String): Call {
        val urlBuilder = HttpUrl.parse(Const.BASE_URL+"users")?.newBuilder()
                ?.addQueryParameter("email", email)
                ?.addQueryParameter("password",password)
        return Request.Builder()
                .url(urlBuilder?.build())
                .get()
                .build()
                .let {
                    client.newCall(it)
                }
    }

    fun authorizeUser(email: String,password: String): Call {
        val urlBuilder = HttpUrl.parse(Const.AUTH_URL)?.newBuilder()
        return Request.Builder()
                .url(urlBuilder?.build())
                .addHeader(Const.CONTENT_TYPE,Const.APPLICATION_JSON)
                .post(FormBody.Builder()
                        .add("email",email)
                        .add("password",password)
                        .build())
                .build()
                .let {
                    client.newCall(it)
                }
    }

    fun getBooks(token:String):Call {
        val urlBuilder = HttpUrl.parse(Const.BASE_URL+"books")?.newBuilder()

        return Request.Builder()
                .url(urlBuilder?.build())
                .addHeader(Const.CONTENT_TYPE,Const.APPLICATION_JSON)
                .addHeader(Const.AUTHORIZATION,token)
                .get()
                .build()
                .let {
                    client.newCall(it)
                }
    }

    fun createNewBook(token: String,bookName:String): Call {
        val urlBuilder = HttpUrl.parse(Const.BASE_URL+"books")?.newBuilder()
        return Request.Builder()
                .url(urlBuilder?.build())
                .addHeader(Const.CONTENT_TYPE,Const.APPLICATION_JSON)
                .addHeader(Const.AUTHORIZATION,token)
                .post(FormBody.Builder()
                        .add("name",bookName)
                        .build())
                .build()
                .let {
                    client.newCall(it)
                }
    }

    fun deleteBook(token: String,bookId: String): Call{
        val urlBuilder = HttpUrl.parse("""${Const.BASE_URL}books/$bookId""")?.newBuilder()

        return Request.Builder()
                .url(urlBuilder?.build())
                .addHeader(Const.CONTENT_TYPE,Const.APPLICATION_JSON)
                .addHeader(Const.AUTHORIZATION,token)
                .delete()
                .build()
                .let {
                    client.newCall(it)
                }
    }

    fun updateBook(token: String,bookId: String,bookName: String): Call{
        val urlBuilder = HttpUrl.parse("""${Const.BASE_URL}books/$bookId""")?.newBuilder()

        return Request.Builder()
                .url(urlBuilder?.build())
                .addHeader(Const.CONTENT_TYPE,Const.APPLICATION_JSON)
                .addHeader(Const.AUTHORIZATION,token)
                .put(FormBody.Builder()
                        .add("name",bookName)
                        .build())
                .build()
                .let {
                    client.newCall(it)
                }
    }


}