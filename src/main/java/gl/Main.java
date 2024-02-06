package gl;

import org.apache.log4j.BasicConfigurator;

import us.codecraft.webmagic.Spider;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();  
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}