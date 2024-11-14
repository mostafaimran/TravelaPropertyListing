This Android app shows available property lists from Travela on a Google map. The list is refreshed each time the map region is updated.
Technology used - Jetpack Compose, Google Map, Retrofit.
The architecture followed - MVVM Clean Architecture.

TO-DO:
Please use your own `Google Map API KEY`, replacing `YOUR_API_KEY` in the manifest - 

`<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="YOUR_API_KEY" />`

However, putting keys in the manifest file is a bad practice. It can be moved to a much more secure place. I Will work on this later.
