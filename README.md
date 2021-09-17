#  [](#mobile-ecommerce-marvel)[Mobile] - Ecommerce Marvel

O aplicativo Android desenvolvido com abordagem arquitetônica MVC e usa a API da Marvel Comics que permite aos desenvolvedores em qualquer lugar acessar informações sobre a vasta biblioteca de quadrinhos da Marvel.


##   Screenshots

<table>
	<tr>
		<th>Loja<th>
		<th>Datalhes<th>
		<th>Carrinho<th>
	<tr>
		<th>
		<img src="https://github.com/PedroTTorres/MarvelComicStore/blob/main/Screenshots/loja.jpeg" width="250px">
		<th>
		<th>
		<img src="https://github.com/PedroTTorres/MarvelComicStore/blob/main/Screenshots/detalhes.jpeg"width="250px">t
		<th>
		<th>
		<img src="https://github.com/PedroTTorres/MarvelComicStore/blob/main/Screenshots/carrinho.jpeg"width="250px">
		<th>
			
<table>

## Referências, bibliotecas e ferramentas utilizadas no projeto

* [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)
Demonstrate possible ways to help with testing, maintaining and extending of an Android app using different architectural concepts and tools.
* [Retrofit](http://square.github.io/retrofit)
A type-safe HTTP client for Android and Java
* [Gson](https://github.com/google/gson)
A Java serialization/deserialization library that can convert Java Objects into JSON and back.
* [Design Support Library](http://developer.android.com/intl/pt-br/tools/support-library/features.html#design)
The Design package provides APIs to support adding material design components and patterns to your apps.
* [RecyclerView](http://developer.android.com/intl/pt-br/reference/android/support/v7/widget/RecyclerView.html)
A flexible view for providing a limited window into a large data set.
* [Picasso](https://square.github.io/picasso/)
A powerful image downloading and caching library for Android
* [NavigationUI](https://developer.android.com/guide/navigation/navigation-ui)
This class contains static methods that manage navigation with the top app bar, the navigation drawer, and bottom navigation.

### Outros conceitos usados

-   RecyclerView
-   Fragments
-   md5 encrypt
-   BottomSheet

## Estrutura do projeto
<BR><BR>
main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── com
│       │   │       └── marvel
│       │   │           └── comicstore
│       │   │               ├── apisetup
│       │   │               │   ├── Authentication.java
│       │   │               │   └── Request.java
│       │   │               ├── config
│       │   │               │   └── RetrofitConfig.java
│       │   │               ├── controller
│       │   │               │   └── MainController.java
│       │   │               ├── interfaces
│       │   │               │   └── Comics.java
│       │   │               ├── model
│       │   │               │   ├── ComicData.java
│       │   │               │   ├── ComicList.java
│       │   │               │   ├── ComicPrice.java
│       │   │               │   ├── DataContainer.java
│       │   │               │   ├── DataWrapper.java
│       │   │               │   └── Image.java
│       │   │               └── ui
│       │   │                   ├── CartComicAdapter.java
│       │   │                   ├── ComicsAdapter.java
│       │   │                   ├── fragment
│       │   │                   │   ├── CartFragment.java
│       │   │                   │   ├── ComicFragment.java
│       │   │                   │   └── StoreFragment.java
│       │   │                   └── MainActivity.java
<BR><BR>
