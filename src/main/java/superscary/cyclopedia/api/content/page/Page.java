package superscary.cyclopedia.api.content.page;

/**
 * Base page constructor
 *
 * @since 1.0.0
 */
public abstract class Page {

    private final int pageNumber;

    public Page () {
        this.pageNumber = pageNumber();
    }

    public abstract void content ();

    public abstract int pageNumber ();

    public final int getPageNumber () {
        return pageNumber;
    }

}
