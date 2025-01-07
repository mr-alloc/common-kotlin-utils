package constant

/**
 * 에러코드 인터페이스, 카테고리고 구분하여 에러코드를 정의할수 있으며, 도메인 구현은 클라이언트에서 한다.
 */
interface ErrorCode {
    val category: String
    val value: Int
}