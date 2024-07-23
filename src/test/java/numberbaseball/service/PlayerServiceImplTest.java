package numberbaseball.service;

import numberbaseball.repository.PlayerRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerServiceImplTest {

    PlayerService playerService = new PlayerServiceImpl(new PlayerRepositoryImpl());

    @Test
    @DisplayName("플레이어 이름이 null 인 경우 실패")
    void playerNameIsNull() {
        boolean result = playerService.playerNameCheck(null);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어 이름이 공백인 경우 실패")
    void playerNameIsEmpty() {
        boolean result = playerService.playerNameCheck("");

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어 이름이 띄어진 공백인 경우 실패")
    void playerNameIsBlank() {
        boolean result = playerService.playerNameCheck("  ");

        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("플레이어 숫자가 공백인 경우 실패")
    void playerNumIsBlank() {
        boolean result = playerService.playerNumCheck("  ");

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어 숫자가 숫자가 아닌 경우 실패")
    void playerNumIsNotDigit() {
        boolean result = playerService.playerNumCheck("test");

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어 숫자가 4글자가 아닌 경우 실패")
    void playerNumIsNot4Length() {
        boolean result = playerService.playerNumCheck("12345");

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어 숫자가 공백으로 구분되는 경우 성공")
    void playerNumIsDistinguishBySpace() {
        boolean result = playerService.playerNumCheck("1 2 3 4");

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어 숫자가 그대로 구분되는 경우 성공")
    void playerNumIsDistinguish() {
        boolean result = playerService.playerNumCheck("1234");

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("선공이 null 인 경우 실패")
    void firstMoveIsNull() {
        boolean result = playerService.playerNumCheck(null);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("선공이 1 또는 2가 아닌 경우 실패")
    void firstMoveCheck() {
        boolean result = playerService.playerNumCheck("3");

        assertThat(result).isFalse();
    }
}