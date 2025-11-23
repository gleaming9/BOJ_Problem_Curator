package BOJ.service;

public interface SolvedAcClient {
    /**
     * solved.ac API를 호출해서 결과를 JSON 문자열로 반환
     *
     */
    String searchProblem(String query);
}
