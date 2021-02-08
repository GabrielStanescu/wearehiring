package tema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Consumer {
	Resume r;
	ArrayList<Consumer> friends;
	
	public Consumer() {
		r = new Resume.ResumeBuilder().build();
		friends = new ArrayList<Consumer>();
	}
	
	public void add(Education education) {
		r.ed.add(education);
		Collections.sort(r.ed);
	}
	
	public void add(Experience experience) {
		r.xp.add(experience);
		Collections.sort(r.xp);
	}
	
	public void add(Consumer consumer) {
		friends.add(consumer);
	}
	
	public int getDegreeInFriendship(Consumer consumer) {
		Level currentCons;
		LinkedList<Level> q = new LinkedList<Level>();
		ArrayList<Consumer> visited = new ArrayList<Consumer>();
		visited.add(this);
		q.add(new Level(this, 0));
		
		while(!q.isEmpty()) {
			currentCons = q.poll();
			if (currentCons.consumer == consumer)
				return currentCons.degree;
			for (int i = 0; i < currentCons.consumer.friends.size(); i++) {
				if (!visited.contains(currentCons.consumer.friends.get(i))) {
					q.add(new Level(currentCons.consumer.friends.get(i), currentCons.degree + 1));
					visited.add(currentCons.consumer.friends.get(i));
				}
			}
		}
		return -1;
	}
	
	public void remove(Consumer consumer) {
		if (friends.contains(consumer))
			friends.remove(consumer);
	}
	
	public Integer getGraduationYear() {
		if (r.ed.size() != 0)
			return r.ed.get(0).getEndDate().getYear();
		return null;
	}
	
	public Double meanGPA() {
		int n = r.ed.size();
		double sum = 0;
		Iterator<Education> i = r.ed.iterator();
		while(i.hasNext())
			sum += i.next().grade;
		if (sum == 0)
			return null;
		else
			return sum / n;
	}
	
	public static class Resume {
		private Information info;
		private ArrayList<Education> ed;
		private ArrayList<Experience> xp;
		
		private Resume(ResumeBuilder builder) {
			info = builder.info;
			ed = builder.ed;
			xp = builder.xp;
		}
		
		public static class ResumeBuilder {
			private Information info;
			private ArrayList<Education> ed;
			private ArrayList<Experience> xp;
			
			public ResumeBuilder() {
				ed = new ArrayList<Education>();
				xp = new ArrayList<Experience>();
				info = new Information();
			}
			
			public ResumeBuilder info(Information info) {
				this.info = info;
				return this;
			}
			
			public ResumeBuilder ed(ArrayList<Education> ed) {
				this.ed = ed;
				return this;
			}
			
			public ResumeBuilder xp(ArrayList<Experience> xp) {
				this.xp = xp;
				return this;
			}
			
			public Resume build() {
				return new Resume(this);
			}
		}
		
		public Information getInfo() {
			return info;
		}
		
		public ArrayList<Education> getEd() {
			return ed;
		}
		
		public ArrayList<Experience> getXp() {
			return xp;
		}
		
		public String toString() {
			return getInfo() + "\n" + getEd() + "\n" + getXp();
		}
		
	}
	
	class Level {
		Consumer consumer;
		int degree;
		
		public Level(Consumer consumer, int degree) {
			this.consumer = consumer;
			this.degree = degree;
		}
	}
}
