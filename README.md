<div align="center">
  <h1>🔍 BOJ Problem Curator</h1>

  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/AWS-EC2-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white">
  
  <br>
  <br>
</div>

> **백준(BOJ) 문제 추천 및 커스텀 검색 API 서버**
> 
> Solved.ac API를 활용하여 사용자가 설정한 조건에 맞는 알고리즘 문제를 선별해 주는 백엔드 서비스입니다.

## 📖 프로젝트 소개

알고리즘 문제 풀이를 즐기는 개발자로서, 기존의 랜덤 디펜스 서비스들이 제공하지 않는 상세한 필터링 기능에 대한 갈증을 느껴 시작한 프로젝트입니다.

단순히 난이도 범위 내의 무작위 문제를 추천받는 방식은 푼 사람 수가 극소수인 마이너한 문제, 번역이 없는 다국어 문제가 추천되는 등 학습 효율을 떨어뜨리는 경우가 많았습니다.

상세한 필터링 기능을 적용해서 원하는 바운더리 내의 문제를 랜덤으로 제공하는 서비스입니다.

### 🚀 주요 기능

* **조건별 문제 검색:** 알고리즘 분류, 난이도 범위, 최소 풀이 수 등 다양한 조건을 조합하여 검색 가능.
* **한국어 문제 필터링:** 다국어 문제 중 한국어 지문이 제공되는 문제만 선별.
* **풀었던 문제 제외:** 사용자의 백준 ID를 입력받아, 이미 해결한 문제는 결과 목록에서 자동으로 제외.
* **Solved.ac API 연동:** 외부 API의 검색 쿼리 문법에 맞춘 요청 생성 및 응답 데이터의 객체 매핑 처리.

---

## 🛠 기술 스택

| Category | Technology |
| --- | --- |
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.2.0 |
| **Build Tool** | Gradle (Kotlin DSL) |
| **Testing** | JUnit 5, AssertJ, Mockito |
| **Utils** | Jackson, Lombok |
| **Infrastructure** | Docker, AWS EC2 (Ubuntu 22.04) |

## 📝 API 사용법

### 문제 검색 API

**Request:**
`GET /api/search`

| Parameter | Type | Required | Description | Example |
| --- | --- | --- | --- | --- |
| `tag` | String | No | 알고리즘 태그 (영문) | `dp`, `bfs`, `dp,greedy` |
| `minTier` | String | No | 최소 난이도 | `g5`, `s1` |
| `maxTier` | String | No | 최대 난이도 | `g1`, `p5` |
| `solvedCount` | Integer | No | 최소 푼 사람 수 | `1000` |
| `isKorean` | Integer | No | 한국어 문제 여부 (1: True) | `1` |
| `userId` | String | No | **제외할 사용자 ID** | `gleaming9` |
| `count` | Integer | No | 반환할 문제 개수 (Default: 1) | `10` |

### 💡 사용 예시 (Examples)

DP 태그가 붙은 문제 1개 추천
> `GET /api/search?tag=dp`

골드 5 ~ 골드 1 사이의 수학 문제 3개 추천
> `GET /api/search?tag=math&minTier=g5&maxTier=g1&count=3`

플래티넘 1 이하의 수학 문제 3개 추천
> `GET /api/search?tag=math&maxTier=p1&count=3`

한국어 지문이 있고, 1000명 이상이 푼 검증된 문제 1개 추천
> `GET /api/search?isKorean=1&solvedCount=1000`

사용자(gleaming9)가 이미 푼 문제는 제외하고 추천
> `GET /api/search?tag=greedy&userId=gleaming9`

 DP, Greedy 태그가 동시에 붙었으며 사용자(gleaming9)가 이미 푼 문제는 제외하고 실버 5 ~ 골드 1 사이의 100명 이상이 푼 한국어 문제 2개 추천
> `GET /api/search?tag=dp,greedy&userId=gleaming9&minTier=s5&maxTier=g1&solvedCount=100&isKorean=1&count=2`

**Response (JSON):**

```json
[
    {
        "problemId": 2839,
        "titleKo": "설탕 배달",
        "level": 7,
        "tierName": "Silver 4",
        "acceptedUserCount": 112542,
        "url": "https://www.acmicpc.net/problem/2839",
        "tags": [
            { "key": "dp" },
            { "key": "math" }
        ]
    }
]
```
---
## 🌐 Live Demo

현재 AWS EC2 인스턴스에 서버가 배포되어 있습니다. 별도의 설치 없이 아래 URL을 통해 API를 즉시 테스트해보실 수 있습니다.

**Base URL:** `http://3.39.233.150:8080`

* **기본 검색 테스트:** [http://3.39.233.150:8080/api/search?tag=dp](http://3.39.233.150:8080/api/search?tag=dp)
* **조건부 검색 테스트:** [http://3.39.233.150:8080/api/search?minTier=g5&maxTier=g1&count=3](http://3.39.233.150:8080/api/search?minTier=g5&maxTier=g1&count=3)
---

## 🐳 설치 및 실행

AWS Live Demo를 사용하는 것이 가장 간편하지만, 직접 서버를 실행해보고 싶다면 아래 방법을 따르세요.

### 1. Docker로 실행
Docker가 설치되어 있다면 한 줄의 명령어로 서버를 실행할 수 있습니다.

```bash
docker run -p 8080:8080 gleam9/boj-problem-curator
```

### 2. 소스 코드로 실행
```bash
git clone https://github.com/gleaming9/BOJ_Problem_Curator.git
cd BOJ_Problem_Curator
./gradlew bootRun
```
