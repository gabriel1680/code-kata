fn main() {
    println!("Hello, world!");
}


pub fn to_roman(mut n: u32) -> String {
    let mut roman = String::from("");
    if n == 9 || n == 4 {
        roman.push('I');
        n += 1;
    }
    if n >= 10 {
        roman.push('X');
        n -= 10;
    }
    if n >= 5 {
        roman.push('V');
        n -= 5;
    }
    for _ in 0..n {
        roman.push('I');
    }
    return roman;
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn translate_to_roman() {
        assert_eq!("I", to_roman(1));
        assert_eq!("II", to_roman(2));
        assert_eq!("III", to_roman(3));
        assert_eq!("IV", to_roman(4));
        assert_eq!("V", to_roman(5));
        assert_eq!("VI", to_roman(6));
        assert_eq!("VII", to_roman(7));
        assert_eq!("VIII", to_roman(8));
        assert_eq!("IX", to_roman(9));
        assert_eq!("X", to_roman(10));
        assert_eq!("XI", to_roman(11));
        assert_eq!("XII", to_roman(12));
        assert_eq!("XIII", to_roman(13));
    }

}
