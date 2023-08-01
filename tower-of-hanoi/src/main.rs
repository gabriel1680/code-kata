fn main() {
    calculate_moviments(2);
    tower_of_hanoi(3);
}

fn tower_of_hanoi(rings: i32) -> i32 {
    rings
}

fn calculate_moviments(disks: u32) -> u32 {
    // (2 ^ n) - 1
    return 2_u32.pow(disks) - 1;
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn calculate_moviments_basic() {
        assert_eq!(calculate_moviments(1), 1);
    }

    #[test]
    fn calculate_moviments_with_several_disks() {
        assert_eq!(calculate_moviments(2), 3);
        assert_eq!(calculate_moviments(3), 7);
        assert_eq!(calculate_moviments(4), 15);
    }

}

