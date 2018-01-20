package pl.put.poznan.buildings.utils;

/**
 * Interface just to globally store values, strings etc.
 */

public interface Constants {

    String APP_TITLE = "Moje przygody z IO";
    String CREATE_BUILDING_TITLE = "Dodaj budynek";
    String CREATE_FLOOR_TITLE = "Dodaj poziom";
    String CREATE_ROOM_TITLE = "Dodaj pomieszczenie";
    String IMPORT_EXTERNAL_JSON = "Importuj dane";
    String EXPORT_EXTERNAL_JSON = "Zapisz dane";
    String FIND_ALERT_TITLE = "Wyszukaj nieunormowane pomieszczenia";
    String ALERT_RESULT_TITLE = "Pomieszczenia przekraczające normy";

    String ID_DESC = "Podaj id";
    String NAME_DESC = "Podaj nazwę";
    String ADD_DESC = "Dodaj";
    String LOAD_DESC = "Wczytaj";
    String SAVE_DESC = "Zapisz";
    String SEARCH_DESC = "Wyszukaj";
    String ALERT_NORM_DESC = "Podaj wartość normy";
    String GO_BACK_DESC = "Anuluj";
    String EXIT_DESC = "Wyjdź z aplikacji";
    String ROOM_ID_DESC = "Id pomieszczenia";
    String ROOM_NAME_DESC = "Nazwa pomieszczenia";
    String AREA_DESC = "Powierzchnia (m^2)";
    String VOLUME_DESC = "Objętość (m^2)";
    String HEAT_DESC = "Zużycie energii";
    String LIGHT_DESC = "Moc oświetlenia";
    String NORM_DESC = "Ustalona norma";
    String CALCULATED_NORM_DESC = "Obliczona norma";

    String JSON_DESC = "Podaj absolutną ścieżkę do pliku";
    String JSON_SAVE_DESC = "Podaj absolutną ścieżkę do pliku, w którym zostanie zapisany wynik";

    String FUN_AREA_DESC = "Oblicz powierzchnię";
    String FUN_VOLUME_DESC = "Oblicz kubaturę";
    String FUN_LIGHT_DESC = "Oblicz oświetlenie na jednostkę powierzchni";
    String FUN_HEAT_DESC = "Oblicz zużycie energii na jednostkę objętości";
    String FUN_ALERT_DESC = "Znajdź pomieszczeni przekraczające normę";

    String FUN_IMPORT_JSON_DESC = "Importuj dane";
    String FUN_EXPORT_JSON_DESC = "Eksportuj dane";

    String FUN_AREA_RESULT_DESC = "Suma powierzchni wyniosła: ";
    String FUN_VOLUME_RESULT_DESC = "Suma kubatury wyniosła: ";
    String FUN_LIGHT_RESULT_DESC = "Zużycie światła na powierzchnię wyniosło: ";
    String FUN_HEAT_RESULT_DESC = "Zużycie energii na powierzchnię wyniosła: ";

    String INVALID_DATA_MESSAGE = "Dane nie są poprawne!";
    String INVALID_NORM_MESSAGE = "Norma nie może być liczbą niedodatnią!";
    String FILE_DOES_NOT_EXISTS = "Podany plik nie istnieje lub brakuje uprawnień do jego odczytania";
    String FILE_DOES_NOT_EXISTS_SAVE = "Podany plik nie istnieje lub brakuje uprawnień do jego zapisu";
    String DELETE_OLD_FILE_FAIL = "Nie udało się usunąć starego pliku pod tą samą nazwą";
    String IMPORT_FAILED = "Import nie powiódł się, upewnij się że obiekty są przechowywane jako tablica";
    String IMPORT_DATA_SET_NON_VALID = "Zbiór danych jest niespójny, posiada duplikaty id";
    String EXPORT_FAILED = "Wystąpił nieoczekiwany błąd przy zapisie do pliku";
    String EXPORT_NO_DATA = "Formularz jest pusty, brak danych do zapisu";


}
