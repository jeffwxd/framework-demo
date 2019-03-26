package com.wxd.service;

import com.wxd.entity.Article;
import com.wxd.entity.Comment;
import com.wxd.repository.ArticleRepository;
import com.wxd.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    public void postComment(Long articleId, String content) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (!articleOptional.isPresent()) {
            throw new RuntimeException("没有对应的文章");
        }
        Article article = articleOptional.get();

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setContent(content);
        commentRepository.save(comment);

        article.setCommentCount(article.getCommentCount() + 1);
        articleRepository.save(article);
    }
}
