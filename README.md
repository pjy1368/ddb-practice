# DynamoDB 실습 프로젝트

Spring Boot 3.x + DynamoDB Local을 사용한 간단한 상품 관리 API입니다.

## 🚀 시작하기

### 1. DynamoDB Local 실행

```bash
# DynamoDB Local + Admin UI 실행
docker-compose up -d

# 실행 확인
docker-compose ps
```

### 2. Spring Boot 애플리케이션 실행

```bash
# 의존성 설치 및 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun
```

### 3. DynamoDB 테이블 초기화

```bash
# 테이블 생성
curl -X POST http://localhost:8080/api/products/init-table
```

## 📋 API 사용법

### 상품 생성
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "테스트 상품",
    "description": "테스트용 상품입니다",
    "price": 29900,
    "category": "전자제품",
    "stock": 10
  }'
```

### 모든 상품 조회
```bash
curl http://localhost:8080/api/products
```

### 특정 상품 조회
```bash
curl http://localhost:8080/api/products/{상품ID}
```

### 상품 수정
```bash
curl -X PUT http://localhost:8080/api/products/{상품ID} \
  -H "Content-Type: application/json" \
  -d '{
    "name": "수정된 상품",
    "description": "수정된 설명",
    "price": 39900,
    "category": "전자제품",
    "stock": 15
  }'
```

### 상품 삭제
```bash
curl -X DELETE http://localhost:8080/api/products/{상품ID}
```

## 🛠️ GUI 도구 사용

### 1. DynamoDB Admin (웹 기반)
- URL: http://localhost:8001
- 브라우저에서 접속하여 테이블과 데이터를 확인할 수 있습니다.

### 2. AWS NoSQL Workbench
1. AWS NoSQL Workbench 다운로드 및 설치
2. Operation Builder에서 "Add connection" 클릭
3. "DynamoDB local" 탭 선택
4. 연결 정보 입력:
   - Connection name: `Local DynamoDB`
   - Port: `8000`
5. "Connect" 클릭

## 🔧 포트 정보

- **Spring Boot**: 8080
- **DynamoDB Local**: 8000
- **DynamoDB Admin**: 8001

## 📁 프로젝트 구조

```
src/main/java/com/example/ddbpractice/
├── DdbPracticeApplication.java     # 메인 애플리케이션
├── config/
│   └── DynamoDbConfig.java         # DynamoDB 설정
├── controller/
│   └── ProductController.java      # REST API 컨트롤러
├── model/
│   └── Product.java                # 상품 엔티티
├── repository/
│   └── ProductRepository.java      # 데이터 액세스 레이어
└── service/
    └── ProductService.java         # 비즈니스 로직 레이어
```

## 🛑 종료하기

```bash
# Spring Boot 애플리케이션 종료 (Ctrl+C)

# Docker 컨테이너 종료
docker-compose down

# 데이터도 함께 삭제하려면
docker-compose down -v
``` 