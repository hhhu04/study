package cat.feed.service;

import cat.feed.entity.Feed;
import cat.feed.repository.FeedRepository;
import cat.feed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public void save(Feed feed,String email) throws Exception{
        feed = feed.newFeed(feed, userRepository.findByUserId(email).get().getId());
        feedRepository.save(feed);
    }



}
