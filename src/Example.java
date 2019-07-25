import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;


public class Example extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject =new MainPageObject(driver);
    }

    @Test
    public void testEx2FindSearch() {
        MainPageObject.waitForElementAndClick(
                "xpath://*[contains(@text, 'Search Wikipedia')]",
                "not find Search Box",
                15
        );
        MainPageObject.waitForElementPresents(
                "xpath://*[contains(@text, 'Search…')]",
                "Cannot find Search input",
                15);

    }


    @Test
    public void testEx3FindWordAndCancelSearch() throws InterruptedException {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Leonardo da Vinci";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We did not find articles",
                amount_of_search_results > 1
        );
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForEmptyResultsLable();
    }

    @Test
    public void testEx5SavingTwoArticles() throws InterruptedException {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        //добавление первой статьи "Leonardo da Vinci"
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Leonardo da Vinci");
        SearchPageObject.clickByArticleWithSubString("Italian Renaissance polymath");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Must to read";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        //Добавление второй статьи "Michelangelo"
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Michelangelo");
        SearchPageObject.clickByArticleWithSubString("Italian sculptor, painter, architect and poet");
        ArticlePageObject.waitForTitleElement();
        String second_article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addToFolderByName(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(second_article_title);
    }


    @Test
    public void testEx6AssertTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Leonardo da Vinci");
        SearchPageObject.clickByArticleWithSubString("Italian Renaissance polymath");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertTitleOfArticlePresent();

    }

}



