package ywm.blog;


import com.wolf.Wolf;
import com.wolf.cloud.WolfCloudlet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ywm.blog.model.*;
import ywm.blog.service.*;
import ywm.blog.term.ArticleTerm;
import ywm.blog.term.ArticleCommentTerm;
import ywm.blog.view.VArticle;
import ywm.blog.view.VComment;

/**
 * Created by Herbert Yu on 2019-08-14 17:41
 */
@Wolf.BootApplication(name = "blog")
@EnableFeignClients
@RestController
@Api(tags = "BlogCloudlet API")
public class BlogCloudlet implements WolfCloudlet {

    public static void main(String[] args) {
        Wolf.run(BlogCloudlet.class, args);
    }

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleCommentService commentService;

    @Autowired
    private ArticleLookService articleLookService;

    @Autowired
    private ArticleLoveService articleLoveService;


    @ApiOperation("文章保存")
    @PostMapping("/article/save")
    public Article articleSave(@RequestBody Article article) {
        return articleService.save(article);
    }

    @ApiOperation("文章删除")
    @DeleteMapping("/article/remove/{id}")
    public boolean articleRemove(@PathVariable String id) {
        return articleService.remove(id);
    }

    @ApiOperation("文章删除")
    @PostMapping("/article/remove")
    public boolean articleRemove(@RequestParam String[] ids) {
        return articleService.remove(ids);
    }

    @ApiOperation("文章状态变更")
    @PostMapping("/article/change/status/{status}")
    public boolean articleChangeStatus(@PathVariable("status") Integer status,
                                       @RequestParam String[] ids) {
        return articleService.changeStatus(status, ids);
    }

    @ApiOperation("文章详情")
    @GetMapping("/article/detail/{id}")
    public VArticle articleDetail(@PathVariable String id) {
        return articleService.detail(id);
    }

    @ApiOperation("文章检索")
    @GetMapping("/article/search")
    public Page<VArticle> articleSearch(ArticleTerm term, Pageable pageable) {
        Page<VArticle> articlePage = articleService.search(term, pageable);
        return articlePage;
    }

    @ApiOperation("文章数量")
    @GetMapping("/article/count")
    public long articleCount(ArticleTerm term) {
        return articleService.count(term);
    }


    /*******************************************************************************/

    @ApiOperation("评论保存")
    @PostMapping("/comment/save")
    public ArticleComment commentSave(@RequestBody ArticleComment comment) {
        return commentService.save(comment);
    }

    @ApiOperation("评论查找")
    @PostMapping("/comment/find/{id}")
    public ArticleComment commentFind(@PathVariable("id") String id) {
        return commentService.findById(id);
    }

    @ApiOperation("评论查找")
    @GetMapping("/comment/search")
    public Page<VComment> commentSearch(ArticleCommentTerm term, Pageable pageable) {
        return commentService.search(term, pageable);
    }

    @ApiOperation("评论删除")
    @DeleteMapping("/comment/remove/{id}")
    public boolean commentRemove(@PathVariable String id) {
        return commentService.remove(id);
    }

    @ApiOperation("评论数量")
    @GetMapping("/comment/count")
    public long commentCount(ArticleCommentTerm term) {
        return commentService.count(term);
    }


    /*******************************************************************************/

    @ApiOperation("标签保存")
    @PostMapping("/tag/save")
    public ArticleTag tagSave(@RequestBody ArticleTag tag) {
        return articleTagService.save(tag);
    }


    @ApiOperation("标签删除")
    @DeleteMapping("/tag/remove/{id}")
    public boolean tagRemove(@PathVariable String id) {
        return articleTagService.remove(id);
    }

    @ApiOperation("标签删除")
    @PostMapping("/tag/remove")
    public boolean tagRemove(@RequestParam String[] ids) {
        return articleTagService.remove(ids);
    }

    @ApiOperation("标签详情")
    @GetMapping("/tag/detail/{id}")
    public ArticleTag tagDetail(@PathVariable String id) {
        return articleTagService.findById(id);
    }

    @ApiOperation("标签数量")
    @GetMapping("/tag/count")
    public long tagCount() {
        return articleTagService.count();
    }

    @ApiOperation("标签查询")
    @GetMapping("/tag/page")
    public Page<ArticleTag> tagPage(Pageable pageable) {
        return articleTagService.page(pageable);
    }


    /*******************************************************************************/
    @ApiOperation("文章查看保存")
    @PostMapping("/look/save")
    public ArticleLook articleLookSave(@RequestBody ArticleLook articleLook) {
        return articleLookService.save(articleLook);
    }

    @ApiOperation("文章点赞保存")
    @PostMapping("/love/save")
    public ArticleLove articleLoveSave(@RequestBody ArticleLove articleLove) {
        return articleLoveService.save(articleLove);
    }
}

