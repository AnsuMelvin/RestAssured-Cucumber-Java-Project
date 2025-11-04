package com.fdmgroup.pojos;

public class Comment {
	   private int id;
	    private int userId;
	    private int foodId;
	    private String body;

	    public Comment() {}

		public Comment(int id, int userId, int foodId, String body) {
			this.id = id;
			this.userId = userId;
			this.foodId = foodId;
			this.body = body;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getFoodId() {
			return foodId;
		}

		public void setFoodId(int foodId) {
			this.foodId = foodId;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}
	    
	   
}
