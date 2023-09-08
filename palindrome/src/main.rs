fn main() {}

pub fn is_palindrome(word: &str) -> bool {
    to_palindrome(word) == word
}

fn to_palindrome(word: &str) -> String {
    word.chars().rev().collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_should_check_for_palindrome() {
        assert!(is_palindrome("ana"));
        assert!(is_palindrome("bob"));
    }

    #[test]
    fn test_should_check_when_not_palindrome() {
        assert_eq!(false, is_palindrome("hello"));
    }
}
