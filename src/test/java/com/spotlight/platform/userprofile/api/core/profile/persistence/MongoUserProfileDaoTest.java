package com.spotlight.platform.userprofile.api.core.profile.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.spotlight.platform.userprofile.api.core.json.JsonMapper;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.MongodStarter;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.embed.process.runtime.Network;
import de.flapdoodle.reverse.StateID;
import de.flapdoodle.reverse.TransitionWalker;
import de.flapdoodle.reverse.Transitions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures.USER_PROFILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MongoUserProfileDaoTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private Transitions transitions;
    private TransitionWalker.ReachedState<RunningMongodProcess> running;
    private MongoClient mongoClient;

    private UserProfileDao userProfileDao;


    @AfterEach
    void tearDown() {
        mongoClient.close();
        running.close();
    }


    @BeforeEach
    void setup() throws Exception {
        this.transitions = Mongod.instance()
                                 .transitions(Version.Main.PRODUCTION);

        running = transitions.walker()
                             .initState(StateID.of(RunningMongodProcess.class));

        var serverAddress = running.current()
                                   .getServerAddress();
        this.mongoClient = MongoClients.create(String.format(CONNECTION_STRING,
                                                             serverAddress.getHost(),
                                                             serverAddress.getPort()));
        this.userProfileDao = new MongoUserProfileDao(mongoClient.getDatabase("test"), JsonMapper.getInstance());

    }

    @Test
    public void shouldInsertInsertNewData() {
        userProfileDao.put(UserProfileFixtures.USER_PROFILE);
        assertThat(userProfileDao.get(UserProfileFixtures.USER_ID)).hasValueSatisfying(
                userProfile -> assertThat(userProfile).usingRecursiveComparison()
                                                      .isEqualTo(USER_PROFILE));
    }

    @Test
    void getNonExistingUser_OptionalEmptyReturned() {
        assertThat(userProfileDao.get(UserProfileFixtures.NON_EXISTING_USER_ID)).isEmpty();
    }


}