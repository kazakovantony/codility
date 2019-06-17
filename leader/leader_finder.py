class LeaderFinder:
    def __init__(self, arg_sequence):
        self.sequence = arg_sequence

    def find_leader_slowest(self):
        n = len(self.sequence)
        leader = -1

        for k in range(n):
            candidate = self.sequence[k]
            count = 0
            for i in range(n):
                if self.sequence[i] == candidate:
                    count += 1
            if count > n // 2:
                leader = candidate
        return leader

    def find_leader_better_but_not_best(self):
        n = len(self.sequence)
        leader = -1
        self.sequence.sort()
        candidate = self.sequence[n // 2]
        count = 0
        for i in range(n):
            if self.sequence[i] == candidate:
                count += 1
            if count > n // 2:
                leader = candidate
        return leader

    def find_leader_best(self):
        n = len(self.sequence)
        size = 0
        # try to find most popular element
        for k in range(n):
            if size == 0:
                size += 1
                value = self.sequence[k]
            else:
                if value != self.sequence[k]:
                    size -= 1
                else:
                    size += 1
        candidate = -1
        # if most popular element exist, assume it is our candidate
        if size > 0:
            candidate = value
        leader = -1
        count = 0
        for z in range(n):
            if self.sequence[z] == candidate:
                count += 1
            if count > n // 2:
                leader = candidate
        return leader

    def equi_leader_count(self):
        count = 0
        n = len(self.sequence)
        for i in range(1, n):
            right_part = self.sequence[:i]
            left_part = self.sequence[i:]
            leader_finder = LeaderFinder(right_part)
            right_leader = leader_finder.find_leader_best()
            leader_finder = LeaderFinder(left_part)
            left_leader = leader_finder.find_leader_best()
            if right_leader == left_leader and right_leader != -1:
                count += 1
        return count

    def equi_leader_count_best(self):
        n = len(self.sequence)
        leader = self.find_leader_best()
        if leader == -1:
            return 0
        leader_count = len([number for number in self.sequence if number == leader])
        count = 0
        leader_count_so_far = 0
        for index in range(n):
            if self.sequence[index] == leader:
                leader_count_so_far += 1
            if leader_count_so_far > (index + 1) // 2 and leader_count - leader_count_so_far > (n - index - 1) // 2:
                count += 1
        return count


sequence = [6, 8, 4, 6, 8, 6, 6]
finder = LeaderFinder(sequence)
print('worse case: ' + f'{finder.find_leader_slowest()}')
print('better case: ' + f'{finder.find_leader_better_but_not_best()}')
print('better case: ' + f'{finder.find_leader_best()}')
sequence = [4, 3, 4, 4, 4, 2]
finder = LeaderFinder(sequence)
print('equi leader: ' + f'{finder.equi_leader_count_best()}')
