fn main() {
    let mut tower = Tower::new(1);
    let _ = tower.current_step();
    let _ = tower.next_step();
}

struct Step {
    pins: Vec<Vec<i32>>,
}

struct Tower {
    disks: i32,
    steps: Vec<Step>,
    current_step: usize,
}

impl Tower {
    pub fn new(disks: i32) -> Tower {
        let pins: Vec<Vec<i32>> = vec![(1..disks + 1).collect(), vec![], vec![]];
        let mut solver = Tower {
            disks,
            steps: vec![Step { pins }],
            current_step: 0,
        };
        solver.solve();
        return solver;
    }

    pub fn next_step(&mut self) -> Result<Vec<Vec<i32>>, String> {
        if self.current_step == self.steps.len() - 1 {
            return Err("Sequence contains no more elements".to_string());
        }
        self.current_step += 1;
        return Ok(self.current_step());
    }

    pub fn solve(&mut self) {
        let initial_state = Step {
            pins: Tower::create_initial_state(self.disks),
        };
        self.steps = vec![initial_state];
        if self.disks > 0 {
            self.hanoi(self.disks, 0, 1, 2)
        }
    }

    fn create_initial_state(disks: i32) -> Vec<Vec<i32>> {
        let mut initial_pin_state = vec![];
        if disks > 0 {
            for n in 1..=disks {
                initial_pin_state.push(n);
            }
        }
        return vec![initial_pin_state, vec![], vec![]];
    }

    fn hanoi(&mut self, disks: i32, from: usize, to: usize, via: usize) {
        if disks == 1 {
            self.move_to(from, to);
        } else {
            self.hanoi(disks - 1, from, via, to);
            self.move_to(from, to);
            self.hanoi(disks - 1, via, to, from);
        }
    }

    fn move_to(&mut self, from: usize, to: usize) {
        let mut last_step = Tower::copy(&self.steps.last().unwrap().pins);
        let disk = last_step[from].pop().unwrap();
        last_step[to].push(disk);
        self.steps.push(Step { pins: last_step });
    }

    fn copy(vec: &Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut result: Vec<Vec<i32>> = vec![];
        for value in vec.iter() {
            result.push(value.to_vec());
        }
        result
    }

    pub fn current_step(&self) -> Vec<Vec<i32>> {
        Tower::copy(&self.steps[self.current_step].pins)
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn empty_tower() {
        let tower = Tower::new(0);
        let expected: Vec<Vec<i32>> = vec![vec![], vec![], vec![]];
        assert_eq!(expected, tower.current_step());
    }

    #[test]
    fn error_when_not_have_more_steps() {
        let mut tower = Tower::new(0);
        let message = String::from("Sequence contains no more elements");
        assert_eq!(Err(message), tower.next_step());
    }

    #[test]
    fn should_resolve_one_disk() {
        let tower = Tower::new(1);
        assert_eq!(vec![vec![1], vec![], vec![]], tower.current_step());
    }

    #[test]
    fn should_resolve_next_step() {
        let mut tower = Tower::new(1);
        assert_eq!(Ok(vec![vec![], vec![1], vec![]]), tower.next_step());
    }

    #[test]
    fn should_resolve_two_disks() {
        let tower = Tower::new(2);
        assert_eq!(vec![vec![1, 2], vec![], vec![]], tower.current_step());
    }

    #[test]
    fn next_step_three_disks() {
        let mut tower = Tower::new(3);
        assert_eq!(Ok(vec![vec![1, 2], vec![3], vec![]]), tower.next_step());
        assert_eq!(Ok(vec![vec![1], vec![3], vec![2]]), tower.next_step());
        assert_eq!(Ok(vec![vec![1], vec![], vec![2, 3]]), tower.next_step());
        assert_eq!(Ok(vec![vec![], vec![1], vec![2, 3]]), tower.next_step());
        assert_eq!(Ok(vec![vec![3], vec![1], vec![2]]), tower.next_step());
        assert_eq!(Ok(vec![vec![3], vec![1, 2], vec![]]), tower.next_step());
        assert_eq!(Ok(vec![vec![], vec![1, 2, 3], vec![]]), tower.next_step());
    }
}
