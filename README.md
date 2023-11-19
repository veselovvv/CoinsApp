# CoinsApp
💰 Android App using Kotlin, Clean Architecture, MVVM, ViewModel, LiveData, Coroutines, Hilt, Navigation Component, View Binding, Retrofit, Gson, Material Design, and Bottom Navigation.

This Android app displays a list of assets retrieved from this API - https://docs.coincap.io/#ee30bea9-bb6b-469d-958a-d3e35d442d7a, as well as detailed information about each asset, including its history and markets. There is also a search for assets. There are also lists of rates, exchanges, and markets with detailed information.

The app uses bottom navigation. There are tabs: assets, rates, exchanges, and markets.

The first tab contains the list of assets. You can also search for assets, and by clicking on an asset, a screen with detailed info about the asset is shown. This screen has two buttons: history and markets, and by clicking on a button, a screen with a list is shown.

The second tab contains rates. By clicking on a rate, a screen with detailed info about the rate is opened. You can also search for rates.

The third tab displays the list of exchanges. You can also search for exchanges, and by clicking on an exchange, a screen with detailed info about the exchange is shown.

The last tab contains the list of markets. You can also search for markets.

In the whole app, when the data is loading, a progress indicator is displayed. In addition, there is a fail screen. It will be displayed if there is no connection, the service is not available, or another error has occurred.
