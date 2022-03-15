package com.buzinasgeekbrains.themoviedb.model

import java.util.*

class RepositoryActorImpl: RepositoryActor {
    override fun getActorFromServer(): List<Actor> {
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
                    "12 Years a Slave (2013) and Alex Jones in Denis Villeneuve's Prisoners (2013)."))
    }

    override fun getActorFromLocalStorage(): List<Actor> {
        TODO("Not yet implemented")
    }
}