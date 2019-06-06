import Component from "@ember/component";
import { computed } from "@ember/object";

export default Component.extend({
  canGoBack: computed("currentPage", function() {
    return this.get("currentPage") > 0;
  }),

  canGoFoward: computed("currentPage", "maxPages", function() {
    return this.get("maxPages") > this.get("currentPage") + 1;
  }),

  previousPage: computed("currentPage", function() {
    return this.get("currentPage") - 1;
  }),

  nextPage: computed("currentPage", function() {
    return this.get("currentPage") + 1;
  }),

  previousPages: computed("currentPage", function() {
    let currentPage = this.get("currentPage");
    switch (currentPage) {
      case 1:
        return [{ isDots: false, pageNumber: 0 }];
      case 2:
        return [
          { isDots: false, pageNumber: 0 },
          { isDots: false, pageNumber: 1 }
        ];
      case 3:
        return [
          { isDots: false, pageNumber: 0 },
          { isDots: false, pageNumber: 1 },
          { isDots: false, pageNumber: 2 }
        ];
      case 4:
        return [
          { isDots: false, pageNumber: 0 },
          { isDots: false, pageNumber: 1 },
          { isDots: false, pageNumber: 2 },
          { isDots: false, pageNumber: 3 }
        ];
      default:
        return [
          { isDots: false, pageNumber: 0 },
          { isDots: true },
          { isDots: false, pageNumber: currentPage - 3 },
          { isDots: false, pageNumber: currentPage - 2 },
          { isDots: false, pageNumber: currentPage - 1 }
        ];
    }
  }),

  nextPages: computed("currentPage", "maxPages", function() {
    const currentPage = this.get("currentPage");
    const lastPage = this.get("maxPages");
    switch (lastPage - currentPage) {
      case 1:
        return [];
      case 2:
        return [{ isDots: false, pageNumber: lastPage - 1 }];
      case 3:
        return [
          { isDots: false, pageNumber: lastPage - 2 },
          { isDots: false, pageNumber: lastPage - 1 }
        ];
      case 4:
        return [
          { isDots: false, pageNumber: lastPage - 3 },
          { isDots: false, pageNumber: lastPage - 2 },
          { isDots: false, pageNumber: lastPage - 1 }
        ];
      case 5:
        return [
          { isDots: false, pageNumber: lastPage - 4 },
          { isDots: false, pageNumber: lastPage - 3 },
          { isDots: false, pageNumber: lastPage - 2 },
          { isDots: false, pageNumber: lastPage - 1 }
        ];
      default:
        return [
          { isDots: false, pageNumber: currentPage + 1 },
          { isDots: false, pageNumber: currentPage + 2 },
          { isDots: false, pageNumber: currentPage + 3 },
          { isDots: true },
          { isDots: false, pageNumber: lastPage - 1 }
        ];
    }
  })
});
