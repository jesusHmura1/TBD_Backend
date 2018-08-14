package com.tbd.twitter;

import javax.annotation.PostConstruct;
import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import twitter4j.FilterQuery;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.json.DataObjectFactory;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@Service
@Configurable
public class TwitterListener {
	@Autowired
	private TwitterStream twitterStream;
	@Autowired
	private MongoTemplate mongo;

	MongoClient mongoo = new MongoClient("127.0.0.1:27017",27017);
	DB database = mongoo.getDB("twitter7");
	DBCollection collection = database.getCollection("twitter");

	@PostConstruct
	public void run() {
		twitterStream.addListener(new StatusListener() {
			public void onStatus(Status status) {
				//System. out. println(status);
			    String ubicacion=status.getUser().getLocation();

                if (ubicacion.indexOf("Chile")>0) {
					System. out. println(ubicacion);

					BasicDBObject tweet;
					tweet = new BasicDBObject("id", status.getId())
							.append("text", status.getText())
							.append("like", status.getFavoriteCount())
							.append("geoLocation", status.getGeoLocation())
							.append("retweet", status.getRetweetCount())
							.append("locationUser", status.getUser().getLocation())
							.append("name", status.getUser().getName())
							.append("followers", status.getUser().getFollowersCount())
							.append("screenName", status.getUser().getScreenName())
							.append("createdAt", status.getUser().getCreatedAt());
					collection.insert(tweet);

					//System. out. println(ubicacion);

                }

	        }

			@Override
			public void onException(Exception arg0) {
								
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
							
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
								
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
								
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
								
			}			
		});
		
	    FilterQuery filter=new FilterQuery();
	    filter.track(new String[]{"futbol","Futbol","FUTBOL","CLUB SOCIAL Y DEPORTIVO COLO COLO","Club social y deportivo COLO COLO"," Club Social y Deportivo COLO COLO","club social y deportivo colo colo","COLO COLO","Colo Colo","colo colo","Universidad de chile","Universidad de Chile","universidad de chile","UNIVERSIDAD DE CHILE","Club Universidad de chile","club Universidad de Chile","Club universidad de chile"," CLUB UNIVERSIDAD DE CHILE","Universidad catolica","Club Universidad catolica","club Universidad Catolica"," Club Universidad catolica","CLUB UNIVERIDAD CATOLICA","Universidad Catolica","universidad catolica","UNIVERSIDAD CATOLICA","Audax italiano la florida","Audax italiano la florida","Audax italiano La Florida","AUDAX ITALIANO LA FLORIDA","Audax italiano","Audax Italiano","audax italiano","AUDAX ITALIANO","Club de deportes provincial Curico unido","Club de Deportes Provincial Curico unido","CLUB DE DEPORTES PROVINCIAL CURICO UNIDO","club de deportes provincial curico unido","Curico unido","Curico Unido","curico unido","CURICO UNIDO","San luis","San Luis","san luis","SAN LUIS","Club de deportes Antofagasta","Club de Deportes Antofagasta","club de deportes antofagasta","CLUB DE DEPORTES ANTOFAGASTA","Antofagasta","antofagasta","ANTOFAGASTA","Deportes iquique","Deportes Iquique","deportes iquique","DEPORTES IQUIQUE","deportes temuco","Deportes temuco","Deportes Temuco","DEPORTES TEMUCO","everton","Everton de viña del mar","Everton de Viña del Mar","everton de viña del mar","EVERTON DE VIÑA DEL MAR","Everton","EVERTON","Club deportivo huachipato","Club Deportivos Huachipato","club deportivo huachipato","CLUB DEPORTIVO HUACHIPATO","huachipato","Huachipato","HUACHIPATO","club deportivo ohiggins","CLub deportivo ohiggins","Club Deportivo Ohiggins","CLUB DEPORTIVO OHIGGINS","ohiggins","Ohiggins","OHIGGINS","Club deportivo palestino","Club Deportivo Palestino","Club deportivo palestino","club deportivo palestino","palestino","Palestino","PALESTINO","Club deportivo universidad de concepcion","club deportivo universidad de concepcion","Club Deportivo Universidad de Concepcion","CLUB DEPORTIVO UNIVERSIDAD DE CONCEPCION","universidad de concepcion","Universidad de concepcion","Universidad de Concepcion","UNIVERSIDAD DE CONCEPCION","union española","Union española","Union Española","UNION ESPAÑOLA","Club de Deportes union la calera","club de deportes union la calera","Club de Deportes Union la Calera","CLUB DE DEPORTES UNION LA CALERA","union la calera","Union la Calera","Union la calera","UNION LA CALERA"});
	    filter.language(new String[]{"es"});
	    twitterStream.filter(filter);

	}

	public TwitterStream getTwitterStream() {
		return twitterStream;
	}

	public void setTwitterStream(TwitterStream twitterStream) {
		this.twitterStream = twitterStream;
	}

	public MongoTemplate getMongo() {
		return mongo;
	}

	public void setMongo(MongoTemplate mongo) {
		this.mongo = mongo;
	}
}
