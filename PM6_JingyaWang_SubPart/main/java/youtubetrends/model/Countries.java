/* by Yiyang Bu */

package youtubetrends.model;

public class Countries {

  protected Integer CountryId;
  protected String CountryName;

  public Countries(Integer CountryId) {
    this.CountryId = CountryId;
  }

  public Countries(Integer CountryId, String CountryName) {
    this.CountryId = CountryId;
    this.CountryName = CountryName;
  }

  public Countries(String countryName) {
	super();
	CountryName = countryName;
}

public Integer getCountryId() {
    return CountryId;
  }

  public void setCountryId(Integer CountryId) {
    this.CountryId = CountryId;
  }

  public String getCountryName() {
    return CountryName;
  }

  public void setCountryName(String CountryName) {
    this.CountryName = CountryName;
  }

}