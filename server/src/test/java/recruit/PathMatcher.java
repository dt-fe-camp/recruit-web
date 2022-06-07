package recruit;

import org.springframework.util.AntPathMatcher;

public class PathMatcher {

  public static void main(String[] args) {
    Boolean isMatch = new AntPathMatcher().match("^(?!/(app|admin)/.*\\.(js|css|html?))", "/app/index.htm2l");
    System.out.println("result: " + isMatch);
  }
}
