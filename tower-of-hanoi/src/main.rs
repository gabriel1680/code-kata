fn main() {
    let mut tower = hanoi(1);
    let _ = tower.current_step();
    let _ = tower.next_step();
}

fn hanoi(disks: i32) -> Solver {
    let value: Vec<Vec<i32>> = vec![(1..disks + 1).collect(), vec![], vec![]];
    Solver::new(disks, value)
}

struct Solver {
    disks: i32,
    value: Vec<Vec<i32>>,
}

impl Solver {
    pub fn new(disks: i32, value: Vec<Vec<i32>>) -> Solver {
        Solver { disks, value }
    }

    pub fn next_step(&mut self) -> Result<Vec<Vec<i32>>, String> {
        if self.disks == self.value[2].len() as i32 {
            return Err("Sequence contains no more elements".to_string());
        }

        for pin_idx in 0..=2 {
            let len = self.value[pin_idx].len();
            if len == 0 {
                continue;
            }
            let idx = len - 1;
            let value = self.value[pin_idx][idx];
            for pin in self.value.iter_mut() {
                if pin.is_empty() || value - 1 == *pin.last().unwrap() {
                    pin.push(value);
                    self.value[pin_idx].remove(idx);
                    return Ok(self.current_step());
                }
            }
        }

        Err(String::from("Nothing was found"))
    }

    pub fn current_step(&self) -> Vec<Vec<i32>> {
        let mut result: Vec<Vec<i32>> = vec![];
        for floor in self.value.iter() {
            result.push(floor.to_vec());
        }
        result
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn empty_tower() {
        let tower = hanoi(0);
        let expected: Vec<Vec<i32>> = vec![vec![], vec![], vec![]];
        assert_eq!(expected, tower.current_step());
    }

    #[test]
    fn error_when_not_have_more_steps() {
        let mut tower = hanoi(0);
        let message = String::from("Sequence contains no more elements");
        assert_eq!(Err(message), tower.next_step());
    }

    #[test]
    fn should_resolve_one_disk() {
        let tower = hanoi(1);
        assert_eq!(vec![vec![1], vec![], vec![]], tower.current_step());
    }

    #[test]
    fn should_resolve_next_step() {
        let mut tower = hanoi(1);
        assert_eq!(Ok(vec![vec![], vec![1], vec![]]), tower.next_step());
    }

    #[test]
    fn should_resolve_two_disks() {
        let tower = hanoi(2);
        assert_eq!(vec![vec![1, 2], vec![], vec![]], tower.current_step());
    }

    #[test]
    fn next_step_three_disks() {
        let mut tower = hanoi(3);
        assert_eq!(Ok(vec![vec![1, 2], vec![3], vec![]]), tower.next_step());
        assert_eq!(Ok(vec![vec![1], vec![3], vec![2]]), tower.next_step());
        assert_eq!(Ok(vec![vec![1], vec![], vec![2, 3]]), tower.next_step());
        assert_eq!(Ok(vec![vec![], vec![1], vec![2, 3]]), tower.next_step());
        //assert_eq!(Ok(vec![vec![], vec![1, 2], vec![3]]), tower.next_step());
    }
}
