package cat.feed.service;

import cat.feed.entity.Feed;
import cat.feed.repository.FeedRepository;
import cat.feed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public void save(Feed feed,String email) throws Exception{
        feed = feed.newFeed(feed, userRepository.findByUserId(email).get().getId());
        feed.setNickName(userRepository.findByUserId(email).get().getNickName());
        feedRepository.save(feed);
    }


    public List<Feed> AllFeed() {
        List<Feed> list = new ArrayList<>();
        list = feedRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        return list;
    }

    public Feed feedDetail(String title, Feed list) {
        list = feedRepository.findFeedByTitle(title);
        return list;
    }
}
