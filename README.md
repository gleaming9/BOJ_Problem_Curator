# 🔍 BOJ Problem Curator

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-EC2-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)

> **백준(BOJ) 문제 추천 및 커스텀 검색 API 서버**
> 
> Solved.ac API를 활용하여 사용자가 설정한 조건에 맞는 알고리즘 문제를 선별해 주는 백엔드 서비스입니다.

## 📖 프로젝트 소개

알고리즘 문제 풀이를 즐기는 개발자로서, 기존의 백준 랜덤 디펜스 서비스가 제공하지 않는 상세한 필터링 기능에 대한 갈증을 느껴 시작한 프로젝트입니다.

단순히 난이도 범위의 아무 문제나 제공받는 것이 아니라, 원하는 필터링을 적용해서 적절한 문제를 제공받고 싶은 필요성을 느끼곤 했습니다.
특히 백준 랜덤 디펜스를 하면서 푼 사람 수 5명 이하, 러시아어 문제와 같은 마이너한 문제를 추천받으면 골머리를 앓곤 했습니다.

푼 사람 수가 적다는 것은 난이도 기여가 극소수의 인원에 의해 결정됨으로 납득하기 힘든 난이도를 책정받은 문제를 제공 받을 수 있습니다.
러시아어 문제, 일본어 문제 등 번역이 절실한 다국어 문제가 추천되어서 문제 풀이에 어려움을 겪기도 했습니다.

이러한 문제를 해결하고 저의 실생활에서 의미있는 프로그램을 만들고자 마음먹고, 상세한 필터링 기능을 적용해서 원하는 바운더리 내의 문제를 랜덤으로 제공하는 프로그램을 만들었습니다.

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

---

## 📝 API 사용법

### 문제 검색 API

**Request:**
`GET /api/search`

| Parameter | Type | Required | Description | Example |
| --- | --- | --- | --- | --- |
| `tag` | String | No | 알고리즘 태그 (영문) | `dp`, `bfs`, `math` |
| `minTier` | String | No | 최소 난이도 | `g5`, `s1` |
| `maxTier` | String | No | 최대 난이도 | `g1`, `p5` |
| `solvedCount` | Integer | No | 최소 푼 사람 수 | `1000` |
| `isKorean` | Integer | No | 한국어 문제 여부 (1: True) | `1` |
| `excludeId` | String | No | **제외할 사용자 ID** | `gleaming9` |
| `count` | Integer | No | 반환할 문제 개수 (Default: 1) | `10` |

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

## 🐳 설치 및 실행

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
