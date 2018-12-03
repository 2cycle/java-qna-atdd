package nextstep.domain;

import nextstep.UnAuthorizedException;
import org.junit.Test;
import support.test.BaseTest;

public class QuestionTest extends BaseTest {

    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    public static final Question question = new Question("test1", "testcontent1");
    public static final Question updateQuestion = new Question("update1", "updatecontent1");

    @Test
    public void update_owner() {
        question.writeBy(JAVAJIGI);
        question.update(JAVAJIGI,updateQuestion);
        softly.assertThat(question).isEqualTo(updateQuestion);
    }

    @Test(expected = UnAuthorizedException.class)
    public void update_noOwner() {
        question.writeBy(JAVAJIGI);
        question.update(SANJIGI,updateQuestion);
        softly.assertThat(question).isEqualTo(updateQuestion);
    }

    @Test
    public void delete_owner() {
        question.writeBy(JAVAJIGI);
        question.deleted(JAVAJIGI);
        softly.assertThat(question.isDeleted()).isTrue();
    }

    @Test(expected = UnAuthorizedException.class)
    public void delete_noOwner() {
        question.writeBy(JAVAJIGI);
        question.deleted(SANJIGI);
    }

}
