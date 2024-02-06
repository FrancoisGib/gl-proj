package gl;

import org.apache.log4j.BasicConfigurator;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Spider.create(new GithubRepoPageProcessor())
                // From "https://github.com/code4craft" began to grasp
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                // Open 5 threads of Crawl
                .thread(5)
                // Start Crawl
                .run();
    }
}