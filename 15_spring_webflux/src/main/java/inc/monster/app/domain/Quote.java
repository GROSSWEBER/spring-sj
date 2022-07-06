package inc.monster.app.domain;

import org.springframework.data.annotation.Id;

public final class Quote {

  private Long id;
  private String book;
  private String content;

  // Empty constructor is required by the data layer and JSON de/ser
  public Quote() {
  }

  public Quote(Long id, String book, String content) {
    this.id = id;
    this.book = book;
    this.content = content;
  }

  public Long getId() {
    return id;
  }

  public String getBook() {
    return book;
  }

  public String getContent() {
    return content;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Quote{");
    sb.append("id='").append(id).append('\'');
    sb.append(", book='").append(book).append('\'');
    sb.append(", content='").append(content).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Quote quote = (Quote) o;
    if (!id.equals(quote.id)) {
      return false;
    }
    if (!book.equals(quote.book)) {
      return false;
    }
    return content.equals(quote.content);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + book.hashCode();
    result = 31 * result + content.hashCode();
    return result;
  }
}
