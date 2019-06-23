# Sports-Club-System


## Spis treści:
1. [Wstęp](#wstęp)
2. [Instalacja](#instalacja)
3. [Napotkane problemy i sposób rozwiązania](#napotkane-problemy-i-sposób-rozwiązania)

+ ## Wstęp

Aplikacja została napisana głównie z myślą zaprezentowania podstawowych umiejętności programowania. Całość jest oparta o wzorzec architektoniczny MVC (Model-View-Controller). Ponadto został dodany kontroler do zmiany ekranu dzięki czemu z łatwością można nanieść zmiany przykładowo zmieniając animację przejścia między oknami. Program wykorzystuje połączenie z internetem dzięki czemu wyświetla aktualną pogodę w danym mieście wykorzystując API Weather oraz przede wszystkim jest oparty o bazę danych. Do stworzenia aplikacji zostały wykorzystane zarówno metody generyczne jak i stworzone na potrzeby aplikacji nowe wyjątki (Exceptions).

![menu](/images/menu.PNG)

## Instalacja

Istnieje kilka możliwości przetestowania programu. Najprostsza z nich wymaga zainstalowania Javy którą można pobrać na następującej stronie.

https://www.java.com/pl/download/

Nastepnie z repozytorium należy pobrać plik ["SportsClubSystem.jar"](https://github.com/MrWiTo/Sports-Club-System/raw/master/SportsClubSystem.jar).


Waga pliku wynosi 15.7 MB - jest to spowodowane tym że plik zawiera potrzebne do działania biblioteki (javafx zajmuje najwięcej) oraz pliki graficzne które umożliwiają funkcjonowanie aplikacji.

Jeśli Java jest prawidłowo zainstalowana na komputerze wystarczy otworzyć wyżej wymieniony plik. Do prawidłowego funkcjonowania wymagane jest połączenie z internetem ponieważ program opiera sie o baze danych znajdującą się na https://remotemysql.com/ oraz wykorzystuje API Weather do pobierania aktualnej pogody w wybranym mieście.


 ## Napotkane problemy i sposób rozwiązania

![schedule](/images/schedule.PNG)

Na początku klasa ScheduleBox zawierała niewidoczny przycisk (Button.setOpacity(0)) na całej powierzchni tego obiektu. W momencie w którym skończyłem robić wygląd okna pojawił się problem odnośnie zmiany widoku. ScreenController jest wymagany do zmiany ekranu i jest on zarazem przekazywany do nowego widoku tuż przed jego usunięciem przez Garbage Collector. Obiekt ScheduleBox jest zupełnie innym obiektem który nie dziedziczy po klasie Button a więc akcja zmiany ekranu musiałaby się odbywać w środku tego obiektu a przekazywanie ScreenControllera do każdego przycisku jest słabym pomysłem. Miałem kilka pomysłów żeby rozwiązać ten problem.

![ScheduleBox](/images/ScheduleBox.PNG)

Najlepszy który postanowiłem wykorzystać w programie polegał na usunięciu niewidzialnego przycisku Button z klasy ScheduleBox. W tym przypadku wiedziałem że obiekty będą znajdować się w ListView - mimo że wyłączyłem widoczność tej listy włącznie z podświetleniem wybranego elementu to są one nadal wybierane po kliknięciu i można je uzyskać w następujący sposób:

```
teamsList.getSelectionModel().selectedItemProperty()
		.addListener((ObservableValue<? extends ScheduleBox> ov, ScheduleBox old_val, ScheduleBox new_val) -> {
			ScheduleBox selectedItem = teamsList.getSelectionModel().getSelectedItem();
			screenController.switchScreen(this, screenController, "gameInfo", selectedItem);
		});
```

Usunięcie przycisku z ScheduleBox było kluczowe nie tylko dlatego, że przycisk ten byłby praktycznie bezużyteczny przy tym rozwiązaniu jednak w momencie gdy był jego częścią po kliknięciu w ten obiekt (ScheduleBox) była jedynie wybierana funkcja przycisku natomiast żaden element nie był zaznaczany w ListView.
