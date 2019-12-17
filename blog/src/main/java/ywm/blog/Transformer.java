package ywm.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ywm.blog.model.Article;
import ywm.blog.service.ArticleLookService;
import ywm.blog.service.ArticleLoveService;
import ywm.blog.service.ArticleTagService;
import ywm.blog.service.ArticleCommentService;
import ywm.blog.term.ArticleLookTerm;
import ywm.blog.term.ArticleLoveTerm;
import ywm.blog.term.ArticleCommentTerm;
import ywm.blog.view.VArticle;

/**
 * Created by Herbert Yu on 2019-12-14 18:26
 */
@Component
public class Transformer {

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleCommentService commentService;

    @Autowired
    private ArticleLookService articleLookService;

    @Autowired
    private ArticleLoveService articleLoveService;


    public VArticle vArticle(Article article) {
        VArticle view = new VArticle(article);
        view.setTagDetail(articleTagService.findByIds(view.getTags()));

        ArticleCommentTerm commentTerm = new ArticleCommentTerm();
        commentTerm.setArticle(view.getId());
        view.setCommitNum(commentService.count(commentTerm));

        ArticleLookTerm articleLookTerm = new ArticleLookTerm();
        articleLookTerm.setArticleId(view.getId());
        view.setLookNum(articleLookService.count(articleLookTerm));

        ArticleLoveTerm articleLoveTerm = new ArticleLoveTerm();
        articleLoveTerm.setArticleId(view.getId());
        view.setLoveNum(articleLoveService.count(articleLoveTerm));
        return view;
    }


}
