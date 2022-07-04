package com.buzinasgeekbrains.themoviedb.model

import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.util.*
import java.util.logging.Handler
import kotlin.concurrent.thread

class RepositoryActorImpl: RepositoryActor {

    override fun getPopularActorsFromServer(): PopularActorsListDTO {
        var popularActorsFromServer: PopularActorsListDTO? = null
        ActorsListLoader.load(object: ActorsListLoader.OnActorLoadListener {

            override fun onLoaded(popularActorsListDTO: PopularActorsListDTO) {
                    popularActorsFromServer = popularActorsListDTO

            }

            override fun onFailed(e: Throwable) {
                Log.e("DEBUGLOG", e.toString(), e)
            }

        })
        Thread.sleep(1000)
        Log.d("JSON", popularActorsFromServer?.results.toString())

        return popularActorsFromServer ?: PopularActorsListDTO(1, emptyList(), 1, 1)
    }

    override fun retrofitGetPopularActorsFromServer(): PopularActorsListDTO {
        var popularActorsFromServer: PopularActorsListDTO? = null
        ActorsListLoader.loadRetrofit(object: ActorsListLoader.OnActorLoadListener {

            override fun onLoaded(popularActorsListDTO: PopularActorsListDTO) {
                popularActorsFromServer = popularActorsListDTO
            }

            override fun onFailed(e: Throwable) {
                Log.e("DEBUGLOG", e.toString(), e)
            }

        })
        Thread.sleep(1000)
        Log.d("JSON", popularActorsFromServer?.results.toString())

        return popularActorsFromServer ?: PopularActorsListDTO(1, emptyList(), 1, 1)
    }

    override fun retrofitGetActorFromServer(): ActorDetailsDTO {
        TODO("Not yet implemented")
    }


    override fun getActorFromServer(): ActorDetailsDTO {
        TODO("Not yet implemented")
    }

    override fun getActorsFromLocalStorage(): List<Actor> {


        return listOf(Actor(17142, "Paul Dano", "1984-06-19", 2,
            "New York City, New York, USA",117.037,
            "Paul Franklin Dano (born June 19, 1984) is an American actor. " +
                    "He began his career on Broadway before making his film debut in The Newcomers" +
                    " (2000). He won the Independent Spirit Award for Best Debut Performance for" +
                    " his role in L.I.E. (2001) and received accolades for his role as" +
                    " Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as" +
                    " Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007)," +
                    " he was nominated for the BAFTA Award for Best Supporting Actor.\n\nDano" +
                    " has also received accolades for roles such as John Tibeats in Steve McQueen's " +
                    "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)."),
            Actor(17144, "Paul Dlkl", "1974-06-19", 1,
                "Moscow Russia",105.037,
                "He began his career on Broadway before making his film debut in The Newcomers" +
                        " (2000). He won the Independent Spirit Award for Best Debut Performance for" +
                        " his role in L.I.E. (2001) and received accolades for his role as" +
                        " Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as" +
                        " Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007)," +
                        " he was nominated for the BAFTA Award for Best Supporting Actor.\n\nDano" +
                        " has also received accolades for roles such as John Tibeats in Steve McQueen's " +
                        "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)." +
                        "He began his career on Broadway before making his film debut in The Newcomers\" +\n" +
                        "                        \" (2000). He won the Independent Spirit Award for Best Debut Performance for\" +\n" +
                        "                        \" his role in L.I.E. (2001) and received accolades for his role as\" +\n" +
                        "                        \" Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as\" +\n" +
                        "                        \" Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007),\" +\n" +
                        "                        \" he was nominated for the BAFTA Award for Best Supporting Actor.\\n\\nDano\" +\n" +
                        "                        \" has also received accolades for roles such as John Tibeats in Steve McQueen's \" +\n" +
                        "                        \"12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)."),
            Actor(17144, "Paul Dlkl", "1974-06-19", 1,
                "Moscow Russia",105.037,
                "He began his career on Broadway before making his film debut in The Newcomers" +
                        " (2000). He won the Independent Spirit Award for Best Debut Performance for" +
                        " his role in L.I.E. (2001) and received accolades for his role as" +
                        " Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as" +
                        " Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007)," +
                        " he was nominated for the BAFTA Award for Best Supporting Actor.\n\nDano" +
                        " has also received accolades for roles such as John Tibeats in Steve McQueen's " +
                        "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)."),
            Actor(17144, "Paul Dlkl", "1974-06-19", 1,
                "Moscow Russia",105.037,
                "He began his career on Broadway before making his film debut in The Newcomers" +
                        " (2000). He won the Independent Spirit Award for Best Debut Performance for" +
                        " his role in L.I.E. (2001) and received accolades for his role as" +
                        " Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as" +
                        " Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007)," +
                        " he was nominated for the BAFTA Award for Best Supporting Actor.\n\nDano" +
                        " has also received accolades for roles such as John Tibeats in Steve McQueen's " +
                        "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)."),
            Actor(17144, "Paul Dlkl", "1974-06-19", 1,
                "Moscow Russia",105.037,
                "He began his career on Broadway before making his film debut in The Newcomers" +
                        " (2000). He won the Independent Spirit Award for Best Debut Performance for" +
                        " his role in L.I.E. (2001) and received accolades for his role as" +
                        " Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as" +
                        " Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007)," +
                        " he was nominated for the BAFTA Award for Best Supporting Actor.\n\nDano" +
                        " has also received accolades for roles such as John Tibeats in Steve McQueen's " +
                        "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)."),
            Actor(17144, "Paul Dlkl", "1974-06-19", 1,
                "Moscow Russia",105.037,
                "He began his career on Broadway before making his film debut in The Newcomers" +
                        " (2000). He won the Independent Spirit Award for Best Debut Performance for" +
                        " his role in L.I.E. (2001) and received accolades for his role as" +
                        " Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as" +
                        " Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007)," +
                        " he was nominated for the BAFTA Award for Best Supporting Actor.\n\nDano" +
                        " has also received accolades for roles such as John Tibeats in Steve McQueen's " +
                        "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)."),
            Actor(17144, "Paul Dlkl", "1974-06-19", 1,
                "Moscow Russia",105.037,
                "He began his career on Broadway before making his film debut in The Newcomers" +
                        " (2000). He won the Independent Spirit Award for Best Debut Performance for" +
                        " his role in L.I.E. (2001) and received accolades for his role as" +
                        " Dwayne Hoover in Little Miss Sunshine (2006). For his dual roles as" +
                        " Paul and Eli Sunday in Paul Thomas Anderson's There Will Be Blood (2007)," +
                        " he was nominated for the BAFTA Award for Best Supporting Actor.\n\nDano" +
                        " has also received accolades for roles such as John Tibeats in Steve McQueen's " +
                        "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013).")
        )
    }
}