package com.wxd.service;

import com.wxd.entity.Article;
import com.wxd.entity.Comment;
import com.wxd.repository.ArticleRepository;
import com.wxd.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional(rollbackFor = Exception.class)
    public void postComment(Long articleId, String content) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        //Optional<Article> articleOptional = articleRepository.findArticleForUpdate(articleId);
        //Optional<Article> articleOptional = articleRepository.findArticleWithPessimisticLock(articleId);
        if (!articleOptional.isPresent()) {
            throw new RuntimeException("没有对应的文章");
        }
        Article article = articleOptional.get();

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setContent(content);
        commentRepository.save(comment);

        int count = articleRepository.updateArticleWithVersion(article.getId(), article.getCommentCount() + 1, article.getVersion());
        if (count == 0) {
            throw new RuntimeException("服务器繁忙,更新数据失败");
        }
        article.setCommentCount(article.getCommentCount() + 1);
        articleRepository.save(article);
       // articleRepository.save(article);
    }
    public static void main(String[] args) {
        System.out.println("第1个月的兔子对数: 1");
        System.out.println("第2个月的兔子对数: 1");
        int f1 = 1, f2 = 1, f, M = 24;
        for (int i = 3; i <= M; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println("第" + i + "个月的兔子对数: " + f2);
        }
    }
}
